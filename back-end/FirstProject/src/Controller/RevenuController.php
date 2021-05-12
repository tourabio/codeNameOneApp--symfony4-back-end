<?php

namespace App\Controller;

use App\Entity\Revenu;
use App\Form\RevenuType;
use App\Repository\RevenuRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Twilio\Rest\Client;

/**
 * @Route("/revenu")
 */
class RevenuController extends AbstractController
{
    /**
     * @Route("/", name="revenu_index", methods={"GET"})
     */
    public function index(RevenuRepository $revenuRepository): Response
    {
        return $this->render('revenu/index.html.twig', [
            'revenus' => $revenuRepository->findAll(),
        ]);
    }

    /**
     * @Route("/rev/new", name="revenue_new", methods={"GET","POST"})
     */
    public function new(Request $request): Response
    {
        $revenu = new Revenu();
        $form = $this->createForm(RevenuType::class, $revenu);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($revenu);
            $entityManager->flush();

            $sid = "ACad346012d1ec310f888215c4692ca4fa";
            $token = "4e0a5c2fb634eb6082ba97f7e8e3ea86";
            $client = new Client($sid, $token);
            $client->messages->create(
                '+21623101558',
                array(
                    'from' => '+15127467326',
                    'body' => "vous etes payé !"
                ));

            return $this->redirectToRoute('revenu_index');
        }

        return $this->render('revenu/new.html.twig', [
            'revenu' => $revenu,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="revenu_show", methods={"GET"})
     */
    public function show(Revenu $revenu): Response
    {
        return $this->render('revenu/show.html.twig', [
            'revenu' => $revenu,
        ]);
    }

    /**
     * @Route("/{id}/edit", name="revenu_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, Revenu $revenu): Response
    {
        $form = $this->createForm(RevenuType::class, $revenu);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('revenu_index');
        }

        return $this->render('revenu/edit.html.twig', [
            'revenu' => $revenu,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="revenu_delete", methods={"POST"})
     */
    public function delete(Request $request, Revenu $revenu): Response
    {
        if ($this->isCsrfTokenValid('delete'.$revenu->getId(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($revenu);
            $entityManager->flush();
        }

        return $this->redirectToRoute('revenu_index');
    }
}
