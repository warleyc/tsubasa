/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MModelCardUpdateComponent } from 'app/entities/m-model-card/m-model-card-update.component';
import { MModelCardService } from 'app/entities/m-model-card/m-model-card.service';
import { MModelCard } from 'app/shared/model/m-model-card.model';

describe('Component Tests', () => {
  describe('MModelCard Management Update Component', () => {
    let comp: MModelCardUpdateComponent;
    let fixture: ComponentFixture<MModelCardUpdateComponent>;
    let service: MModelCardService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MModelCardUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MModelCardUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MModelCardUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MModelCardService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MModelCard(123);
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
        const entity = new MModelCard();
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
