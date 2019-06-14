/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MGuildEffectLevelDeleteDialogComponent } from 'app/entities/m-guild-effect-level/m-guild-effect-level-delete-dialog.component';
import { MGuildEffectLevelService } from 'app/entities/m-guild-effect-level/m-guild-effect-level.service';

describe('Component Tests', () => {
  describe('MGuildEffectLevel Management Delete Component', () => {
    let comp: MGuildEffectLevelDeleteDialogComponent;
    let fixture: ComponentFixture<MGuildEffectLevelDeleteDialogComponent>;
    let service: MGuildEffectLevelService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MGuildEffectLevelDeleteDialogComponent]
      })
        .overrideTemplate(MGuildEffectLevelDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MGuildEffectLevelDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MGuildEffectLevelService);
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
