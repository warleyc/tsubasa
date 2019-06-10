/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MDeckRarityConditionDescriptionUpdateComponent } from 'app/entities/m-deck-rarity-condition-description/m-deck-rarity-condition-description-update.component';
import { MDeckRarityConditionDescriptionService } from 'app/entities/m-deck-rarity-condition-description/m-deck-rarity-condition-description.service';
import { MDeckRarityConditionDescription } from 'app/shared/model/m-deck-rarity-condition-description.model';

describe('Component Tests', () => {
  describe('MDeckRarityConditionDescription Management Update Component', () => {
    let comp: MDeckRarityConditionDescriptionUpdateComponent;
    let fixture: ComponentFixture<MDeckRarityConditionDescriptionUpdateComponent>;
    let service: MDeckRarityConditionDescriptionService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MDeckRarityConditionDescriptionUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MDeckRarityConditionDescriptionUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MDeckRarityConditionDescriptionUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MDeckRarityConditionDescriptionService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MDeckRarityConditionDescription(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new MDeckRarityConditionDescription();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
