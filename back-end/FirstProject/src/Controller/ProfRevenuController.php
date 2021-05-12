<?php

namespace App\Controller;

use App\Entity\Depense;
use App\Entity\Revenu;
use App\Repository\DepenseRepository;
use App\Repository\ReclamationRepository;
use App\Repository\RevenuRepository;
use App\Repository\UserRepository;
use Knp\Snappy\Pdf;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;

class ProfRevenuController extends AbstractController
{
    /**
     * @Route("/prof/revenu", name="prof_revenu")
     */
    public function index(RevenuRepository $revenuRepository): Response
    {
        $revenus = $revenuRepository->findBy(array('idutilisateur'=> '12345676'));
        return $this->render('prof_revenu/index.html.twig', [
            'revenu' => $revenus
        ]);
    }



    /**
     * @Route("/prof/recu/{id}/", name="prof_recu" ,methods={"GET","POST"})
     */
    public function pdf_recu(Request $request, Revenu $revenu , RevenuRepository $revenuRepository
        , UserRepository $userRepository
    ): Response
    {

        $knpSnappyPdf = new Pdf('D:\wkhtmltopdf_32\bin\wkhtmltopdf.exe');

        $revenu = $revenuRepository->find($revenu->getId());
        $usr = $userRepository->find($revenu->getIdutilisateur());
        $html = $this->render('base/pdfRecipe.html.twig', [
            'depense' => $revenu , 'user' => $usr
        ]);
        //  $knpSnappyPdf->getOutputFromHtml( $html);
        header('Content-Type: application/pdf');
        echo $knpSnappyPdf->getOutputFromHtml($html);

        return $this->render('prof_revenu/index.html.twig', [
            'depenses' => $revenu
        ]);
    }



    /**
     * @Route("/revenu/{search}", name="prof_revenu_search", methods={"GET","POST"})
     */
    public function search(Request $request
        , UserRepository $userRepository
        ,NormalizerInterface $Normalizer
        ,RevenuRepository $revenuRepository
    ): Response
    {
        $search_input = $request->get('search_input') ;
        $user = $userRepository->find(12345676);
        $revenus = $revenuRepository->findByDescription( $search_input , $user);
        $jsonContent = $Normalizer->normalize($revenus, 'json',['groups'=> 'rev:read' , 'enable_max_depth' => true]);
        $retour=json_encode($jsonContent);
        return new Response($retour);
    }
}
