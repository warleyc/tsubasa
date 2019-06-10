/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MActionRarityUpdateComponent } from 'app/entities/m-action-rarity/m-action-rarity-update.component';
import { MActionRarityService } from 'app/entities/m-action-rarity/m-action-rarity.service';
import { MActionRarity } from 'app/shared/model/m-action-rarity.model';

describe('Component Tests', () => {
  describe('MActionRarity Management Update Component', () => {
    let comp: MActionRarityUpdateComponent;
    let fixture: ComponentFixture<MActionRarityUpdateComponent>;
    let service: MActionRarityService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MActionRarityUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MActionRarityUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MActionRarityUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MActionRarityService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MActionRarity(123);
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
        const entity = new MActionRarity();
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
