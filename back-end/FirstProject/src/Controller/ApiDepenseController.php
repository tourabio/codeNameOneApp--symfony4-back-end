<?php

namespace App\Controller;

use App\Entity\Depense;
use App\Entity\Recommendation;
use App\Repository\DepenseRepository;
use App\Repository\FicheFinanceRepository;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use App\Repository\RecommendationRepository;
use App\Repository\UserRepository;
use Knp\Bundle\SnappyBundle\Snappy\Response\PdfResponse;
use Knp\Snappy\Pdf;
use Symfony\Component\Serializer\Encoder\JsonEncoder;
use Symfony\Component\Serializer\Encoder\XmlEncoder;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;

/**
 * @Route("/api/depense")
 */

class ApiDepenseController extends AbstractController
{
    /**
     * @Route("/", name="api_depense")
     */
    public function index(DepenseRepository $depenseRepository, Request $request): Response
    {
        $depenses = $depenseRepository->findAll();

        foreach ($depenses as $rec) {
            $date = $rec->getDate();
            $rec->setDateFormatted($date->format('Y-m-d H:i:s'));
        }


        $encoders = array(new XmlEncoder(), new JsonEncoder());
        $normalizers = array(new ObjectNormalizer());
        $normalizer = new ObjectNormalizer();
        $normalizer->setCircularReferenceLimit(2);
        // Add Circular reference handler
        $normalizer->setCircularReferenceHandler(function ($object) {
            return $object->getId();
        });
        $normalizers = array($normalizer);
        $serializer = new Serializer($normalizers, $encoders);
        $formatted = $serializer->normalize($depenses);
        return new JsonResponse($formatted);
    }



    /**
     * @Route("/delete/{id}", name="depense_deleteApi", methods={"POST"})
     */
    public function delete(Request $request, Depense $depense): Response
    {
        $entityManager = $this->getDoctrine()->getManager();
        $entityManager->remove($depense);
        $entityManager->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize("depense supprimer");
        return new JsonResponse($formatted);
    }


    /**
     * @Route("/add", name="depense_add", methods={"GET","POST"})
     */
    public function new(Request $request, UserRepository $userRepository, FicheFinanceRepository $ff): Response
    {
        $depense = new Depense();
        $depense->setValeur($request->get('valeur'));
        $depense->setSource($request->get('source'));
        $depense->setDescription($request->get('description'));
        $depense->setJustificatif($request->get('justificatif'));
        $depense->setDate(new \DateTime());
        $depense->setIdutilisateur($userRepository->find($request->get('idUser')));
        $depense->setIdFinance($ff->find($request->get('idFinance')));
        $entityManager = $this->getDoctrine()->getManager();
        $entityManager->persist($depense);
        $entityManager->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($depense);
        return new JsonResponse($formatted);
    }

    /**
     * @Route("/edit", name="depense_editApi", methods={"GET","POST"})
     */
    public function edit(Request $request, UserRepository $userRepository, DepenseRepository $dr, FicheFinanceRepository $ff): Response
    {
        $idDepense = $request->get('id');
        $em = $this->getDoctrine()->getManager();
        $depense  =  $dr->find($idDepense);


        $depense->setValeur($request->get('valeur'));
        $depense->setSource($request->get('source'));
        $depense->setDescription($request->get('description'));
        $depense->setJustificatif($request->get('justificatif'));
        $depense->setDate(new \DateTime());
        $depense->setIdutilisateur($userRepository->find($request->get('idUser')));
        $depense->setIdFinance($ff->find($request->get('idFinance')));

        $em->persist($depense);
        $em->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize("depense modifier");
        return new JsonResponse($formatted);
    }



    /**
     * @Route("/sort", name="api_depense_sort")
     */
    public function sort(DepenseRepository $depenseRepository, Request $request): Response
    {
        $depenses = $depenseRepository->sortByDate();
        foreach ($depenses as $rec) {
            $date = $rec->getDate();
            $rec->setDateFormatted($date->format('Y-m-d H:i:s'));
        }
        $encoders = array(new XmlEncoder(), new JsonEncoder());
        $normalizers = array(new ObjectNormalizer());
        $normalizer = new ObjectNormalizer();
        $normalizer->setCircularReferenceLimit(2);
        // Add Circular reference handler
        $normalizer->setCircularReferenceHandler(function ($object) {
            return $object->getId();
        });
        $normalizers = array($normalizer);
        $serializer = new Serializer($normalizers, $encoders);
        $formatted = $serializer->normalize($depenses);
        return new JsonResponse($formatted);
    }

    
    /**
     * @Route("/getrecu/{id}/", name="student_recu_api" ,methods={"GET","POST"})
     */
    public function pdf_recu(Request $request, Depense $depense , DepenseRepository $depenseRepository
    , UserRepository $userRepository)
    {

        $knpSnappyPdf = new Pdf('D:\wkhtmltopdf\bin\wkhtmltopdf.exe');

        $depense = $depenseRepository->find($depense->getId());
        $usr = $userRepository->find($depense->getIdutilisateur());
       /* $html = $this->render('base/pdfRecipe.html.twig', [
            'depense' => $depense , 'user' => $usr
        ]);*/
      //  $knpSnappyPdf->getOutputFromHtml( $html);
        header('Content-Type: application/pdf');
        //echo $knpSnappyPdf->getOutputFromHtml($html);


        $knpSnappyPdf->generateFromHtml(
            $this->renderView('base/pdfRecipe.html.twig', [
                'depense' => $depense , 'user' => $usr
            ]
            ),
            'D:/base/recuN'.$depense->getId().'.pdf'
        );

        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize("recu generated");
        return new JsonResponse($formatted);
    }
}
