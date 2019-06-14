/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MTicketQuestStageDeleteDialogComponent } from 'app/entities/m-ticket-quest-stage/m-ticket-quest-stage-delete-dialog.component';
import { MTicketQuestStageService } from 'app/entities/m-ticket-quest-stage/m-ticket-quest-stage.service';

describe('Component Tests', () => {
  describe('MTicketQuestStage Management Delete Component', () => {
    let comp: MTicketQuestStageDeleteDialogComponent;
    let fixture: ComponentFixture<MTicketQuestStageDeleteDialogComponent>;
    let service: MTicketQuestStageService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MTicketQuestStageDeleteDialogComponent]
      })
        .overrideTemplate(MTicketQuestStageDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MTicketQuestStageDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MTicketQuestStageService);
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
