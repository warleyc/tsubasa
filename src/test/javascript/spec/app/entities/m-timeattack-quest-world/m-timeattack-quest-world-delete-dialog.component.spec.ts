/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MTimeattackQuestWorldDeleteDialogComponent } from 'app/entities/m-timeattack-quest-world/m-timeattack-quest-world-delete-dialog.component';
import { MTimeattackQuestWorldService } from 'app/entities/m-timeattack-quest-world/m-timeattack-quest-world.service';

describe('Component Tests', () => {
  describe('MTimeattackQuestWorld Management Delete Component', () => {
    let comp: MTimeattackQuestWorldDeleteDialogComponent;
    let fixture: ComponentFixture<MTimeattackQuestWorldDeleteDialogComponent>;
    let service: MTimeattackQuestWorldService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MTimeattackQuestWorldDeleteDialogComponent]
      })
        .overrideTemplate(MTimeattackQuestWorldDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MTimeattackQuestWorldDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MTimeattackQuestWorldService);
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
