/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MHomeMarqueeDetailComponent } from 'app/entities/m-home-marquee/m-home-marquee-detail.component';
import { MHomeMarquee } from 'app/shared/model/m-home-marquee.model';

describe('Component Tests', () => {
  describe('MHomeMarquee Management Detail Component', () => {
    let comp: MHomeMarqueeDetailComponent;
    let fixture: ComponentFixture<MHomeMarqueeDetailComponent>;
    const route = ({ data: of({ mHomeMarquee: new MHomeMarquee(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MHomeMarqueeDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MHomeMarqueeDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MHomeMarqueeDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mHomeMarquee).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
