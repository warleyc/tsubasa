/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MCardRarityUpdateComponent } from 'app/entities/m-card-rarity/m-card-rarity-update.component';
import { MCardRarityService } from 'app/entities/m-card-rarity/m-card-rarity.service';
import { MCardRarity } from 'app/shared/model/m-card-rarity.model';

describe('Component Tests', () => {
  describe('MCardRarity Management Update Component', () => {
    let comp: MCardRarityUpdateComponent;
    let fixture: ComponentFixture<MCardRarityUpdateComponent>;
    let service: MCardRarityService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MCardRarityUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MCardRarityUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MCardRarityUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MCardRarityService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MCardRarity(123);
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
        const entity = new MCardRarity();
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
