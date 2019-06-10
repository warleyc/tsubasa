/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MActionRarityDeleteDialogComponent } from 'app/entities/m-action-rarity/m-action-rarity-delete-dialog.component';
import { MActionRarityService } from 'app/entities/m-action-rarity/m-action-rarity.service';

describe('Component Tests', () => {
  describe('MActionRarity Management Delete Component', () => {
    let comp: MActionRarityDeleteDialogComponent;
    let fixture: ComponentFixture<MActionRarityDeleteDialogComponent>;
    let service: MActionRarityService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MActionRarityDeleteDialogComponent]
      })
        .overrideTemplate(MActionRarityDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MActionRarityDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MActionRarityService);
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
