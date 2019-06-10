/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MCardIllustAssetsUpdateComponent } from 'app/entities/m-card-illust-assets/m-card-illust-assets-update.component';
import { MCardIllustAssetsService } from 'app/entities/m-card-illust-assets/m-card-illust-assets.service';
import { MCardIllustAssets } from 'app/shared/model/m-card-illust-assets.model';

describe('Component Tests', () => {
  describe('MCardIllustAssets Management Update Component', () => {
    let comp: MCardIllustAssetsUpdateComponent;
    let fixture: ComponentFixture<MCardIllustAssetsUpdateComponent>;
    let service: MCardIllustAssetsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MCardIllustAssetsUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MCardIllustAssetsUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MCardIllustAssetsUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MCardIllustAssetsService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MCardIllustAssets(123);
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
        const entity = new MCardIllustAssets();
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
