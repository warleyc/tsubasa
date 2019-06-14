/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MTimeattackQuestStageDeleteDialogComponent } from 'app/entities/m-timeattack-quest-stage/m-timeattack-quest-stage-delete-dialog.component';
import { MTimeattackQuestStageService } from 'app/entities/m-timeattack-quest-stage/m-timeattack-quest-stage.service';

describe('Component Tests', () => {
  describe('MTimeattackQuestStage Management Delete Component', () => {
    let comp: MTimeattackQuestStageDeleteDialogComponent;
    let fixture: ComponentFixture<MTimeattackQuestStageDeleteDialogComponent>;
    let service: MTimeattackQuestStageService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MTimeattackQuestStageDeleteDialogComponent]
      })
        .overrideTemplate(MTimeattackQuestStageDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MTimeattackQuestStageDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MTimeattackQuestStageService);
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
