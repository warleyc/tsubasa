/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MLuckWeeklyQuestStageDeleteDialogComponent } from 'app/entities/m-luck-weekly-quest-stage/m-luck-weekly-quest-stage-delete-dialog.component';
import { MLuckWeeklyQuestStageService } from 'app/entities/m-luck-weekly-quest-stage/m-luck-weekly-quest-stage.service';

describe('Component Tests', () => {
  describe('MLuckWeeklyQuestStage Management Delete Component', () => {
    let comp: MLuckWeeklyQuestStageDeleteDialogComponent;
    let fixture: ComponentFixture<MLuckWeeklyQuestStageDeleteDialogComponent>;
    let service: MLuckWeeklyQuestStageService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MLuckWeeklyQuestStageDeleteDialogComponent]
      })
        .overrideTemplate(MLuckWeeklyQuestStageDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MLuckWeeklyQuestStageDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MLuckWeeklyQuestStageService);
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
