/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MWeeklyQuestStageDeleteDialogComponent } from 'app/entities/m-weekly-quest-stage/m-weekly-quest-stage-delete-dialog.component';
import { MWeeklyQuestStageService } from 'app/entities/m-weekly-quest-stage/m-weekly-quest-stage.service';

describe('Component Tests', () => {
  describe('MWeeklyQuestStage Management Delete Component', () => {
    let comp: MWeeklyQuestStageDeleteDialogComponent;
    let fixture: ComponentFixture<MWeeklyQuestStageDeleteDialogComponent>;
    let service: MWeeklyQuestStageService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MWeeklyQuestStageDeleteDialogComponent]
      })
        .overrideTemplate(MWeeklyQuestStageDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MWeeklyQuestStageDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MWeeklyQuestStageService);
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
