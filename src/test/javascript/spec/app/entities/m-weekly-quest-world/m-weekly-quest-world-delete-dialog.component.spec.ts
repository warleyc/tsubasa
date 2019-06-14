/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MWeeklyQuestWorldDeleteDialogComponent } from 'app/entities/m-weekly-quest-world/m-weekly-quest-world-delete-dialog.component';
import { MWeeklyQuestWorldService } from 'app/entities/m-weekly-quest-world/m-weekly-quest-world.service';

describe('Component Tests', () => {
  describe('MWeeklyQuestWorld Management Delete Component', () => {
    let comp: MWeeklyQuestWorldDeleteDialogComponent;
    let fixture: ComponentFixture<MWeeklyQuestWorldDeleteDialogComponent>;
    let service: MWeeklyQuestWorldService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MWeeklyQuestWorldDeleteDialogComponent]
      })
        .overrideTemplate(MWeeklyQuestWorldDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MWeeklyQuestWorldDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MWeeklyQuestWorldService);
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
