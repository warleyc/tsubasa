/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MTriggerEffectRangeDeleteDialogComponent } from 'app/entities/m-trigger-effect-range/m-trigger-effect-range-delete-dialog.component';
import { MTriggerEffectRangeService } from 'app/entities/m-trigger-effect-range/m-trigger-effect-range.service';

describe('Component Tests', () => {
  describe('MTriggerEffectRange Management Delete Component', () => {
    let comp: MTriggerEffectRangeDeleteDialogComponent;
    let fixture: ComponentFixture<MTriggerEffectRangeDeleteDialogComponent>;
    let service: MTriggerEffectRangeService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MTriggerEffectRangeDeleteDialogComponent]
      })
        .overrideTemplate(MTriggerEffectRangeDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MTriggerEffectRangeDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MTriggerEffectRangeService);
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
