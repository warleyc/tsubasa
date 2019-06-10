/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MDeckRarityConditionDescriptionDeleteDialogComponent } from 'app/entities/m-deck-rarity-condition-description/m-deck-rarity-condition-description-delete-dialog.component';
import { MDeckRarityConditionDescriptionService } from 'app/entities/m-deck-rarity-condition-description/m-deck-rarity-condition-description.service';

describe('Component Tests', () => {
  describe('MDeckRarityConditionDescription Management Delete Component', () => {
    let comp: MDeckRarityConditionDescriptionDeleteDialogComponent;
    let fixture: ComponentFixture<MDeckRarityConditionDescriptionDeleteDialogComponent>;
    let service: MDeckRarityConditionDescriptionService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MDeckRarityConditionDescriptionDeleteDialogComponent]
      })
        .overrideTemplate(MDeckRarityConditionDescriptionDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MDeckRarityConditionDescriptionDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MDeckRarityConditionDescriptionService);
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
