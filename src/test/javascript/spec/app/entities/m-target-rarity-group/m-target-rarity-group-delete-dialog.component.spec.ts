/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MTargetRarityGroupDeleteDialogComponent } from 'app/entities/m-target-rarity-group/m-target-rarity-group-delete-dialog.component';
import { MTargetRarityGroupService } from 'app/entities/m-target-rarity-group/m-target-rarity-group.service';

describe('Component Tests', () => {
  describe('MTargetRarityGroup Management Delete Component', () => {
    let comp: MTargetRarityGroupDeleteDialogComponent;
    let fixture: ComponentFixture<MTargetRarityGroupDeleteDialogComponent>;
    let service: MTargetRarityGroupService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MTargetRarityGroupDeleteDialogComponent]
      })
        .overrideTemplate(MTargetRarityGroupDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MTargetRarityGroupDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MTargetRarityGroupService);
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
