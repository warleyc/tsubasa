/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MGachaTicketDeleteDialogComponent } from 'app/entities/m-gacha-ticket/m-gacha-ticket-delete-dialog.component';
import { MGachaTicketService } from 'app/entities/m-gacha-ticket/m-gacha-ticket.service';

describe('Component Tests', () => {
  describe('MGachaTicket Management Delete Component', () => {
    let comp: MGachaTicketDeleteDialogComponent;
    let fixture: ComponentFixture<MGachaTicketDeleteDialogComponent>;
    let service: MGachaTicketService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MGachaTicketDeleteDialogComponent]
      })
        .overrideTemplate(MGachaTicketDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MGachaTicketDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MGachaTicketService);
      mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
      mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));
    });
  });
});
